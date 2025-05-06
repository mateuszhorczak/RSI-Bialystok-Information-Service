<script setup lang="ts">
import type { Event } from '~/types'

const eventData = ref<Event>()
</script>

<template>
  <MoleculesFormFindByName v-model:event-data="eventData" />
  <!-- TODO: OrganismEvents instead MoleculesEvent when result in array -->
  <MoleculesEvent
    v-if="eventData"
    class="mt-8"
    :date="eventData.date"
    :description="eventData.description"
    :month="eventData.month"
    :name="eventData.name"
    :type="eventData.type"
    :week="eventData.week"
    :year="eventData.year"
  />
</template>
