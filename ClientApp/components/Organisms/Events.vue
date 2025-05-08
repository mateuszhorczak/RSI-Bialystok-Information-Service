<script setup lang="ts">
const eventStore = useEventStore()
</script>

<template>
  <MoleculesEvent
    v-for="event in eventStore.events"
    :id="event.id"
    :key="event.id"
    :name="event.name"
    :description="event.description"
    :type="event.type"
    :week="event.week"
    :month="event.month"
    :year="event.year"
    :date="event.date"
  />
</template>
