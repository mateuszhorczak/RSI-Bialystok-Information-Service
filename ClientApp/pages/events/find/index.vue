<script setup lang="ts">
import type { Event } from '~/types'

const eventData = ref<Event>()

const eventStore = useEventStore()
eventStore.clearEventsList()
</script>

<template>
  <AtomsSubHeading class="my-4">Wyszukaj wydarzenia po nazwie</AtomsSubHeading>
  <MoleculesFormFindByName v-model:event-data="eventData" />
  <OrganismsEvents />
</template>
