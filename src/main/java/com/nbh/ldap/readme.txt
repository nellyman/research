

Notes on the LDAP set of classes.

Information from the LDAP server is received into getSchemaInfo. Re-reads from 
the LDAP server can be triggered by the getInfo() method.

getSchema provides two Vectors, accessed by the methods getAllRequired() and getAllOptional().


LDAPpan sets up the GUI for the frame. Provides two textFields (svr name and svr port), a button
with LDAPlistener as an ActionListener.

Two JLists are added and are populated by calling methods setRequiredBox and setOptionalBox.
These methods in turn call getSchemaInfo.getAllRequired() -or- getAllOptional()

These lists have a mouselistener associated with it which LDAPlistener.

LDAPlistener has actionlistener and mouseadapter functions.
ActionListener picks up the values in LDAPpan textfields and updates the server that to be requested
in the getSchemaInfo.setServer(), getSchemaInfo.setPort(). The function then calls LDAPpan.setRequiredBox()
and setOptionalBox().

The mouseAdapter is associated with the lists. It needs to pick up the name on the list,
create a LDAPAttributeSchema object and then create a new Frame populated with information.