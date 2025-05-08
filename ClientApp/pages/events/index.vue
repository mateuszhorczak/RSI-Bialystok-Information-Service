<script setup lang="ts">
const eventStore = useEventStore()

await eventStore.getEventsByName('')
</script>

<template>
  <OrganismsEvents />
</template>
