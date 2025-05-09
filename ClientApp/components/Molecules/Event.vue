<script setup lang="ts">
import type { Event } from '~/types'

defineProps<Event>()
</script>

<template>
  <div class="shadow-lg p-4 space-y-1 rounded-lg mx-4 my-4">
    <AtomsSubHeading v-if="name">
      {{ name }}
    </AtomsSubHeading>
    <AtomsParagraph v-if="type">
      typ: {{ type }}
    </AtomsParagraph>
    <AtomsParagraph v-if="date">
      {{ date }}
    </AtomsParagraph>
    <AtomsParagraph v-if="week">
      tydzień roku: {{ week }}
    </AtomsParagraph>
    <AtomsParagraph v-if="month">
      miesiąc: {{ month }}
    </AtomsParagraph>
    <AtomsParagraph v-if="year">
      rok: {{ year }}
    </AtomsParagraph>
    <AtomsParagraph v-if="description">
      opis: {{ description }}
    </AtomsParagraph>
  </div>
</template>
