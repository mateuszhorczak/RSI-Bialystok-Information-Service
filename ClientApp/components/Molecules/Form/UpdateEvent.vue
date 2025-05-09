<script setup lang="ts">
import * as z from 'zod'
import type { FormSubmitEvent } from '@nuxt/ui'
import { CalendarDate } from '@internationalized/date'
import { getWeekNumber } from '~/utils'
import type { BaseEvent } from '~/types'

const props = defineProps<BaseEvent>()

const eventStore = useEventStore()

const [year, month, day]: number[] = props.date.split('-').map(Number)
const date = shallowRef(new CalendarDate(year, month, day))

const schema = z.object({
  name: z.string().trim().min(2),
  type: z.string().trim().min(2),
  description: z.string().trim().min(2),
})

type Schema = z.output<typeof schema>

const state = reactive<Partial<Schema>>({
  name: props.name,
  type: props.type,
  description: props.description,
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

  await eventStore.updateEvent(newEvent)
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
      icon="i-mdi-calendar-edit"
      label="Edytuj"
      size="lg"
      type="submit"
    />
  </UForm>
</template>
