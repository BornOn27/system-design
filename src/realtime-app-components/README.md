## Auto-Completion Advantages
- We can have pre-computed results for the auto-completion, and we can directly
serve from the cache instead of hitting DB
- We can update the auto-completes based on recent activity of user-behaviour, traffic, etc
- If Data not frequently updated, we can store entire data itself, but if it is changing
  then we can at least store the ids to be fetched from DB.
- Major application uses this for Searches  