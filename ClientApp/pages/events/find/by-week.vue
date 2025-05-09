<script setup lang="ts">
const eventStore = useEventStore()
eventStore.clearEventsList()
</script>

<template>
  <AtomsSubHeading>Wyszukaj wydarzenia po tygodniu</AtomsSubHeading>
  <MoleculesFormFindByWeek />
  <OrganismsEvents />
</template>
