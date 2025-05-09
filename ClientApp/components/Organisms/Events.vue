<script setup lang="ts">
const eventStore = useEventStore()
</script>

<template>
  <div class="grid xl:grid-cols-3 lg:grid-cols-2 md:grid-cols-1">
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
  </div>
</template>
