<script setup lang="ts">
const eventStore = useEventStore()
</script>

<template>
  <!-- TODO: use id instead name for key in for -->
  <MoleculesEvent
    v-for="event in eventStore.events"
    :key="event.name"
    :name="event.name"
    :description="event.description"
    :type="event.type"
    :week="event.week"
    :month="event.month"
    :year="event.year"
    :date="event.date"
  />
</template>
