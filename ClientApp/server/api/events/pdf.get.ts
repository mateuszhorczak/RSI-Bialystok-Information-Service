import soap from 'soap'
import { defineEventHandler, getQuery } from 'h3'

export default defineEventHandler(async (event) => {
  const query = getQuery(event)
  const month = query.month
  const year = query.year

  if (!month || !year) {
    throw createError({
      statusCode: 400,
      statusMessage: 'Missing week or year parameter',
    })
  }

  const wsdlUrl = 'https://localhost:8443/eventservice?wsdl'

  try {
    const client = await soap.createClientAsync(wsdlUrl)

    const result = await new Promise((resolve, reject) => {
      // @ts-expect-error silence error
      client.getEventsReportPDF({ month, year }, (err, result) => {
        // eslint-disable-next-line @typescript-eslint/no-unused-expressions
        err ? reject(err) : resolve(result)
      })
    })

    //   @ts-expect-error silence error
    const base64 = result.return
    const buffer = Buffer.from(base64, 'base64')

    event.node.res.setHeader('Content-Type', 'application/pdf')
    event.node.res.setHeader('Content-Disposition', 'inline; filename="report.pdf"')

    return buffer
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
