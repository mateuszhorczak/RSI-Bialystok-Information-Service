<script setup lang="ts">
import * as z from 'zod'
import type { FormSubmitEvent } from '@nuxt/ui'

const eventStore = useEventStore()

const schema = z.object({
  month: z.number(),
  year: z.number(),
})

type Schema = z.output<typeof schema>

const state = reactive<Partial<Schema>>({
  month: undefined,
  year: undefined,
})

async function onSubmit(event: FormSubmitEvent<Schema>) {
  await eventStore.getEventPdf(event.data.month, event.data.year)
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
      label="Miesiąc"
      name="event-month"
    >
      <UInput
        v-model="state.month"
        type="number"
        placeholder="2"
      />
    </UFormField>

    <UFormField
      label="Rok"
      name="event-year"
    >
      <UInput
        v-model="state.year"
        type="number"
        placeholder="2025"
      />
    </UFormField>

    <AtomsButtonContained
      icon="i-mdi-file-export"
      label="Eksportuj"
      size="lg"
      type="submit"
    />
  </UForm>
</template>
