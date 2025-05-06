<script setup lang="ts">
import type { Event } from '~/types'

const eventData = ref<Event>()
</script>

<template>
  <MoleculesFormFindByName v-model:event-data="eventData" />
  <OrganismsEvents />
</template>
