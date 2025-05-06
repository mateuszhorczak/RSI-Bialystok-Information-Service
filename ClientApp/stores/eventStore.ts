import type { Event } from '~/types'

export const useEventStore = defineStore('eventStore', () => {
  const events = ref<Event[]>([])

  const addEvent = async () => {
    console.log('add event')
  }

  const updateEvent = async () => {
    console.log('update event')
  }

  const getEventPdf = async () => {
    console.log('getEventPdf')
  }

  const getEventsByDate = async (dateString: string) => {
    try {
      const { data } = await useFetch('/api/events/date', {
        method: 'GET',
        query: { date: dateString },
      })

      events.value = data.value?.data || null
    }
    catch (error) {
      console.error(error)
    }
  }

  return {
    events,
    addEvent,
    updateEvent,
    getEventPdf,
    getEventsByDate,
  }
})
