<script setup lang="ts">
import * as z from 'zod'
import type { FormSubmitEvent } from '@nuxt/ui'
import type { Event } from '~/types'

const eventData = defineModel<Event>('eventData')

const schema = z.object({
  name: z.string().trim().min(2),
})

type Schema = z.output<typeof schema>

const state = reactive<Partial<Schema>>({
  name: undefined,
})

async function onSubmit(event: FormSubmitEvent<Schema>) {
  try {
    const { data, error: fetchError } = await useFetch('/api/events/name', {
      method: 'GET',
      query: { name: event.data.name },
    })

    if (fetchError.value) {
      throw new Error(fetchError.value.message)
    }

    if (data.value?.status === 'error') {
      throw new Error(data.value.message)
    }

    eventData.value = data.value?.data || null
  }
  catch (err) {
    console.error('Fetch error:', err)
  }
}
</script>

<template>
  <UForm
    :schema="schema"
    :state="state"
    class="space-y-4"
    @submit="onSubmit"
  >
    <UFormField
      label="Nazwa wydarzenia"
      name="event-name"
    >
      <UInput v-model="state.name" />
    </UFormField>

    <AtomsButtonContained
      icon="i-mdi-calendar-search"
      label="Wyszukaj"
      size="lg"
      type="submit"
    />
  </UForm>
</template>
