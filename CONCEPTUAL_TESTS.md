# Test Cases for Charging Stations API

# Test Cases for ChargingStationController

## Test Case 1: Get a charging station by UUID

- **Description:** Test the endpoint for getting a charging station by UUID.
- **Request:** GET `/charging-stations/{uuid}`
- **Expected Response:** A `ChargingStationDTO` object with the corresponding UUID. HTTP status code 200.
- **Test Steps:**
    1. Create a charging station.
    2. Send a GET request to `/charging-stations/{uuid}` with the UUID of the created charging station.
- **Expected Result:** The returned charging station should match the created one.
- **Negative Scenario:** Send a GET request to `/charging-stations/{uuid}` with a non-existing UUID. The expected
  response is HTTP status code 404.

## Test Case 2: Get all or a subset of charging stations in a radius

- **Description:** Test the endpoint for getting all or a subset of charging stations in a radius. The parameters for
  longitude, latitude, and radius are optional.
- **Request:** GET `/charging-stations?longitude={longitude}&latitude={latitude}&radius={radius}`
- **Expected Response:** A list of `ChargingStationDTO` objects within the specified radius. If no parameters are
  provided, all charging stations are returned. HTTP status code 200.
- **Test Steps:**
    1. Create multiple charging stations with different coordinates.
    2. Send a GET request to `/charging-stations` with a specific longitude, latitude, and radius.
    3. Send a GET request to `/charging-stations` without any parameters.
- **Expected Result:** The returned list should only include charging stations within the specified radius for the first
  request, and all charging stations for the second request.

## Test Case 3: Create a charging station

- **Description:** Test the endpoint for creating a charging station.
- **Request:** POST `/charging-stations`
- **Expected Response:** The created `ChargingStationDTO` object. HTTP status code 201.
- **Test Steps:**
    1. Send a POST request to `/charging-stations` with a `ChargingStationDTO` object in the request body.
- **Expected Result:** The returned charging station should match the one in the request body.
- **Negative Scenario:** Send a POST request to `/charging-stations` with a `ChargingStationDTO` object that has an
  existing UUID. The expected response is HTTP status code 400.

## Test Case 4: Update a charging station

- **Description:** Test the endpoint for updating a charging station.
- **Request:** PUT `/charging-stations/{uuid}`
- **Expected Response:** The updated `ChargingStationDTO` object. HTTP status code 200.
- **Test Steps:**
    1. Create a charging station.
    2. Send a PUT request to `/charging-stations/{uuid}` with a `ChargingStationUpdateDTO` object in the request body.
- **Expected Result:** The returned charging station should reflect the updates made.
- **Negative Scenario:** Send a PUT request to `/charging-stations/{uuid}` with a `ChargingStationUpdateDTO` object and
  a non-existing UUID. The expected response is HTTP status code 404.

## Test Case 5: Delete a charging station

- **Description:** Test the endpoint for deleting a charging station.
- **Request:** DELETE `/charging-stations/{uuid}`
- **Expected Response:** HTTP status code 204.
- **Test Steps:**
    1. Create a charging station.
    2. Send a DELETE request to `/charging-stations/{uuid}` with the UUID of the created charging station.
- **Expected Result:** The charging station should be deleted and not retrievable.
- **Negative Scenario:** Send a DELETE request to `/charging-stations/{uuid}` with a non-existing UUID. The expected
  response is HTTP status code 404.