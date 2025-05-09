<script setup lang="ts">
const eventStore = useEventStore()

await eventStore.getEventsByName('')
</script>

<template>
  <AtomsSubHeading class="my-4">
    Wszystkie wydarzenia
  </AtomsSubHeading>
  <OrganismsEvents />
</template>
