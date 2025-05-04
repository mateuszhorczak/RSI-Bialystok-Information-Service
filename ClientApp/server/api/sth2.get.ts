import { defineEventHandler } from 'h3'
import { createClientAsync } from 'soap'

export default defineEventHandler(async () => {
  try {
    const client = await createClientAsync('http://localhost:3000/soap-api/geoip?wsdl')
    client.setEndpoint('http://localhost:3000/soap-api/geoip')

    const [result] = await client.getProductsAsync({})

    return result.return
  }
  catch (error) {
    console.error('Error calling SOAP service:', error)
    return {
      error: 'Failed to get products',
      // @ts-expect-error: Unknow type
      details: error.message,
    }
  }
})
