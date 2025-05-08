<script setup lang="ts">
const eventStore = useEventStore()

await eventStore.getEventsByName('')
</script>

<template>
  <AtomsSubHeading>Wszystkie wydarzenia</AtomsSubHeading>
  <OrganismsEvents />
</template>
