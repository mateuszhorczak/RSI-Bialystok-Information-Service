import type { Event, BaseEvent } from '~/types'

export const useEventStore = defineStore('eventStore', () => {
  const events = ref<Event[]>([])

  const clearEventsList = () => {
    events.value.length = 0
  }

  const addEvent = async (event: Event) => {
    try {
      await useFetch('/api/events', {
        method: 'POST',
        body: event,
      })
    }
    catch (error) {
      console.error(error)
    }
  }

  const updateEvent = async (event: Event) => {
    try {
      await useFetch('/api/events/single', {
        method: 'PATCH',
        body: event,
      })
    }
    catch (error) {
      console.error(error)
    }
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

  const getEventsByName = async (name: string) => {
    try {
      const { data } = await useFetch('/api/events/name', {
        method: 'GET',
        query: { name: name },
      })

      events.value = data.value?.data || null
    }
    catch (error) {
      console.error(error)
    }
  }

  const getEventsByWeek = async (week: number, year: number) => {
    try {
      const { data } = await useFetch('/api/events/week', {
        method: 'GET',
        query: { week: week, year: year },
      })

      events.value = data.value?.data || null
    }
    catch (error) {
      console.error(error)
    }
  }

  const eventId = ref(0)
  const { data: singleEvent, refresh: refreshSingleEvent, error: errorFetchSingleEvent } = useFetch<BaseEvent>('/api/events/single', {
    query: { id: eventId },
    // @ts-expect-error silence transform type error
    transform: response => response.data,
    lazy: true,
    watch: [eventId],
  })

  const fetchSingleEvent = async (id: number) => {
    clearEventsList()
    eventId.value = id
    await refreshSingleEvent()
    if (errorFetchSingleEvent.value) {
      console.error(errorFetchSingleEvent.value)
    }
  }

  return {
    events,
    addEvent,
    updateEvent,
    getEventPdf,
    getEventsByDate,
    getEventsByName,
    getEventsByWeek,
    clearEventsList,
    singleEvent,
    fetchSingleEvent,
  }
})
