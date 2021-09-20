# TaskManager points of improvements

- IMHO: Process should not have a "kill" processes. It is forced by task definition, but I believe killing a process
  should be done in "upper" level (e.g. it is OS responsibility, not a process responsibility). Having possibility to
  kill process outside of process manager could lead to resource leak (process manager has no idea that process he is
  managing was killed)
- Not clear access pattern. Do we need to ensure read after write consistency? How protective we should be on a reading
  part?
- It is stated that PM limit should be defined in compile time. It is hard to test, so in case it is really MUST, I
  would do a private implementation with flexible limit, and public implementation with Hardcoded compile-time. For
  simplicity this additional layer was omitted
- Should priority PM kill the process that is "skipped" (according to doc it is just skipped, whatever it means)? Should
  we kill the process when process should be replaced?
- How much process to we have?