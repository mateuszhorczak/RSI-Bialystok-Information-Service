<script setup lang="ts">
const eventStore = useEventStore()

const router = useRouter()
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
      class="cursor-pointer"
      @click="router.push(`/events/${event.id}`)"
    />
  </div>
</template>
