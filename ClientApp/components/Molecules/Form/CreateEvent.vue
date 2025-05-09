<script setup lang="ts">
import * as z from 'zod'
import type { FormSubmitEvent } from '@nuxt/ui'
import { CalendarDate } from '@internationalized/date'
import { getWeekNumber } from '~/utils'

const eventStore = useEventStore()

const currentDate = new Date()
const date = shallowRef(new CalendarDate(currentDate.getFullYear(), currentDate.getMonth() + 1, currentDate.getDate()))

const schema = z.object({
  name: z.string().trim().min(2),
  type: z.string().trim().min(2),
  description: z.string().trim().min(2),
})

type Schema = z.output<typeof schema>

const state = reactive<Partial<Schema>>({
  name: undefined,
  type: undefined,
  description: undefined,
})

async function onSubmit(event: FormSubmitEvent<Schema>) {
  const newEvent = {
    id: 0,
    name: event.data.name,
    description: event.data.description,
    type: event.data.type,
    date: date.value.toString(),
    month: date.value.month,
    year: date.value.year,
    week: getWeekNumber(date.value),
  }

  await eventStore.addEvent(newEvent)
  const router = useRouter()
  await router.push('/events')
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
      label="Nazwa eventu"
      name="event-name"
    >
      <UInput
        v-model="state.name"
      />
    </UFormField>

    <UFormField
      label="Typ eventu"
      name="event-type"
    >
      <UInput
        v-model="state.type"
      />
    </UFormField>

    <UFormField
      label="Opis"
      name="event-description"
    >
      <UInput
        v-model="state.description"
      />
    </UFormField>

    <UFormField
      label="Data"
      name="event-date"
    >
      <AtomsButtonCalendar v-model:date="date" />
    </UFormField>
    <AtomsButtonContained
      icon="i-mdi-calendar-plus"
      label="Stwórz"
      size="lg"
      type="submit"
    />
  </UForm>
</template>
