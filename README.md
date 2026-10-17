# File Picker Android

Reusable Android/Compose file-picker UI and controller contracts.

## Responsibilities

- Own file-picker state, models, controller contracts, and shared Compose screen.
- Expose user choices and picker actions through callbacks/events.
- Keep UI reusable across consuming apps and features.

## Boundaries

- Do not own broad storage-access workflows or long-term persisted permissions; use `services:storage-access-android` where appropriate.
- Do not hardcode app navigation, destinations, or business rules.
- Do not place file-management services here; use the owning service module.

## Notes

- Uses `org.connecttag.kotlin.compose`.
- Public UI should stay stateless and previewed.
