/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Android\\android_base\\IpcDemo\\ipc_service_aidl_service\\src\\main\\aidl\\com\\android\\work\\ipc\\aidl\\IServiceCallBack.aidl
 */
package com.android.work.ipc.aidl;
// Declare any non-default types here with import statements

public interface IServiceCallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements IServiceCallBack
{
private static final String DESCRIPTOR = "com.android.work.ipc.aidl.IServiceCallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.android.work.ipc.aidl.IServiceCallBack interface,
 * generating a proxy if needed.
 */
public static IServiceCallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof IServiceCallBack))) {
return ((IServiceCallBack)iin);
}
return new Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_addNewBook:
{
data.enforceInterface(DESCRIPTOR);
Book _arg0;
if ((0!=data.readInt())) {
_arg0 = Book.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addNewBook(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements IServiceCallBack
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void addNewBook(Book newBook) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((newBook!=null)) {
_data.writeInt(1);
newBook.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addNewBook, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_addNewBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void addNewBook(Book newBook) throws android.os.RemoteException;
}
