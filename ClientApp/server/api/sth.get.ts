import { defineEventHandler, getQuery } from 'h3'
import { createClientAsync } from 'soap'

export default defineEventHandler(async (event) => { // TODO: change it, template
  try {
    const query = getQuery(event)
    const ipAddress = query.ip || '8.8.8.8'

    const client = await createClientAsync('http://localhost:3000/soap-api/geoip?wsdl')
    client.setEndpoint('http://localhost:3000/soap-api/geoip')

    const [result] = await client.getCountryByIPAsync({ arg0: ipAddress })
    const rawValue = result.return

    const startIndex = rawValue.indexOf('<Country>') + '<Country>'.length
    const endIndex = rawValue.indexOf('</Country>')
    return rawValue.substring(startIndex, endIndex).trim()
  }
  catch (error) {
    console.error('Error calling SOAP service:', error)
    return {
      error: 'Failed to get country information',
      // @ts-expect-error: Unknow type
      details: error.message,
    }
  }
})
