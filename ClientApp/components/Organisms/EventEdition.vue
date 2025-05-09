<script setup lang="ts">
const route = useRoute()
const eventStore = useEventStore()

const id = route.params.id
const id_num = parseInt(id as string, 10)
await eventStore.fetchSingleEvent(id_num)
</script>

<template>
  <AtomsSubHeading class="my-4">Edycja wydarzenia</AtomsSubHeading>
  <MoleculesFormUpdateEvent
    v-if="eventStore.singleEvent"
    :id="eventStore.singleEvent.id"
    :name="eventStore.singleEvent.name"
    :type="eventStore.singleEvent.type"
    :date="eventStore.singleEvent.date"
    :description="eventStore.singleEvent?.description || ''"
  />
</template>
