Feature: validate playlist api
Scenario: verify if create playlist is working
Given create playlist api payload
When user calls with POST http request for create playlist
Then API call executed with status code 201

Scenario: verify if fetch playlist functionality
Given GET playlist api payload
When user calls with GET http request
Then API call executes with status code 200

Scenario: verify if update playlist functionality
Given update playlist api payload
When user calls with PUT http request
Then API call should executes with status code 201