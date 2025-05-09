<script setup lang="ts">
const eventStore = useEventStore()
eventStore.clearEventsList()
</script>

<template>
  <AtomsSubHeading>Wyszukaj wydarzenia po dacie</AtomsSubHeading>
  <MoleculesFormFindByDate />
  <OrganismsEvents />
</template>
