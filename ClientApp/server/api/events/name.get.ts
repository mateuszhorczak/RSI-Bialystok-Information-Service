import soap from 'soap'
import { defineEventHandler, getQuery } from 'h3'

export default defineEventHandler(async (event) => {
  const query = getQuery(event)
  const name = query.name

  if (!name) {
    throw createError({
      statusCode: 400,
      statusMessage: 'Missing name parameter',
    })
  }

  const wsdlUrl = 'https://localhost:8443/eventservice?wsdl'

  try {
    const client = await soap.createClientAsync(wsdlUrl)

    const result = await new Promise((resolve, reject) => {
      // @ts-expect-error silence error
      client.getEventByName({ name }, (err, result) => {
        // eslint-disable-next-line @typescript-eslint/no-unused-expressions
        err ? reject(err) : resolve(result)
      })
    })

    return {
      status: 'success',
      // @ts-expect-error silence error
      data: result.return,
    }
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
