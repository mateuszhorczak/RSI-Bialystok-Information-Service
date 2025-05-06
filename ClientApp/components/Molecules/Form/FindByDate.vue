<script setup lang="ts">
import { CalendarDate } from '@internationalized/date'

const currentDate = new Date()
const date = shallowRef(new CalendarDate(currentDate.getFullYear(), currentDate.getMonth() + 1, currentDate.getDate()))
const eventStore = useEventStore()

async function onSubmit() {
  const dateString = date.value.toString()
  await eventStore.getEventsByDate(dateString)
}
</script>

<template>
  <UButtonGroup>
    <AtomsButtonCalendar v-model:date="date" />
    <AtomsButtonContained
      icon="i-mdi-calendar-search"
      label="Wyszukaj"
      size="lg"
      type="button"
      @click="onSubmit"
    />
  </UButtonGroup>
</template>
