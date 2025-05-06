<script setup lang="ts">
import { type CalendarDate, DateFormatter, getLocalTimeZone } from '@internationalized/date'

const date = defineModel<CalendarDate>('date')

const df = new DateFormatter('pl-PL', {
  dateStyle: 'medium',
})
</script>

<template>
  <UPopover>
    <UButton
      color="neutral"
      variant="subtle"
      icon="i-mdi-calendar"
    >
      {{ date ? df.format(date.toDate(getLocalTimeZone())) : 'Wybierz datę' }}
    </UButton>

    <template #content>
      <UCalendar
        v-model="date"
        class="p-2"
      />
    </template>
  </UPopover>
</template>
