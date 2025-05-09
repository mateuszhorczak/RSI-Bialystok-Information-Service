import soap from 'soap'
import { defineEventHandler } from 'h3'
import type { Event } from '~/types'

export default defineEventHandler(async (event) => {
  const body = await readBody(event)
  if (!body.name.trim() || !body.description.trim() || !body.type.trim()) {
    throw createError({
      statusCode: 400,
      statusMessage: 'Missing parameters',
    })
  }
  const eventToUpdate: Event = { ...body }
  const wsdlUrl = 'https://localhost:8443/eventservice?wsdl'

  try {
    const client = await soap.createClientAsync(wsdlUrl)

    await new Promise((resolve, reject) => {
      // @ts-expect-error silence error
      client.updateEvent({ event: eventToUpdate }, (err, result) => {
        // eslint-disable-next-line @typescript-eslint/no-unused-expressions
        err ? reject(err) : resolve(result)
      })
    })
  }
  catch (error) {
    console.error('SOAP Error:', error)
    throw createError({
      statusCode: 500,
      statusMessage: 'Internal Server Error',
      // @ts-expect-error silence error
      data: error.message,
    })
  }
})
