##Adapter Pattern

- Adapt the behaviour and provide it to client
- Component exposes some feature but our client requires different.
exposing/modifying some features as client might require.
Also called as Wrapper class.

#Intent 
- convert the interface of one class to the interface of clients class. 
- Allowing classes to work together that couldn't have otherwise.
- Future proof client implementaion, that depend on Adapter interfaces, rather than concrete classes. 

- Many number of implementations 
e.g. different different analytics 
Firbase analytics
Biadu Analytics
Alibaba Analytics
XYZ Analytics 

each analytics can have its own Adapter implementation and common Adapter interface can be exposed to client to implement. 


#Related patterns -

Facade pattern
Repository pattern
Strategy pattern 

Module should be open fot extension, but closed to modification. 
