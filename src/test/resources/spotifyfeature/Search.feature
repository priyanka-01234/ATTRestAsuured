Feature: Search song
Scenario: search a song
Given Get a search song payload
| songname | type | artist |
| Main Hoon Saath Tere | track | Arijit Singh |
When user calls with GET request
Then API executes with status code as 200