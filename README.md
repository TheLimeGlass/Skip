# Skip
A Skript addon for making language utilities

```
on script load:
	set {_list::*} to "one", "two" and "three"
	broadcast "%reversed {_list::*}%"
	broadcast "%{_list::*} without ""three""%"
	run in 2 seconds:
		broadcast reversed "test skip"
```
