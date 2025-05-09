<script setup lang="ts">
import * as z from 'zod'
import type { FormSubmitEvent } from '@nuxt/ui'

const eventStore = useEventStore()

const schema = z.object({
  name: z.string().trim().min(2),
})

type Schema = z.output<typeof schema>

const state = reactive<Partial<Schema>>({
  name: undefined,
})

async function onSubmit(event: FormSubmitEvent<Schema>) {
  await eventStore.getEventsByName(event.data.name)
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
