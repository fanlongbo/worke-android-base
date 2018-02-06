/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: F:\\Android\\android_base\\IpcDemo\\ipc_service_aidl_service\\src\\main\\aidl\\com\\android\\work\\ipc\\aidl\\IBookeManager.aidl
 */
package com.android.work.ipc.aidl;
public interface IBookeManager extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements IBookeManager
{
private static final String DESCRIPTOR = "com.android.work.ipc.aidl.IBookeManager";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.android.work.ipc.aidl.IBookeManager interface,
 * generating a proxy if needed.
 */
public static IBookeManager asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof IBookeManager))) {
return ((IBookeManager)iin);
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
case TRANSACTION_addBook:
{
data.enforceInterface(DESCRIPTOR);
Book _arg0;
if ((0!=data.readInt())) {
_arg0 = Book.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.addBook(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_getBookList:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<Book> _result = this.getBookList();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
case TRANSACTION_regionListion:
{
data.enforceInterface(DESCRIPTOR);
com.android.work.ipc.aidl.IServiceCallBack _arg0;
_arg0 = com.android.work.ipc.aidl.IServiceCallBack.Stub.asInterface(data.readStrongBinder());
this.regionListion(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_unRegionListion:
{
data.enforceInterface(DESCRIPTOR);
com.android.work.ipc.aidl.IServiceCallBack _arg0;
_arg0 = com.android.work.ipc.aidl.IServiceCallBack.Stub.asInterface(data.readStrongBinder());
this.unRegionListion(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements IBookeManager
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
@Override public void addBook(Book book) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((book!=null)) {
_data.writeInt(1);
book.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addBook, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.util.List<Book> getBookList() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<Book> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getBookList, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(Book.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void regionListion(com.android.work.ipc.aidl.IServiceCallBack callBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callBack!=null))?(callBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_regionListion, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void unRegionListion(com.android.work.ipc.aidl.IServiceCallBack callBack) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((callBack!=null))?(callBack.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_unRegionListion, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_addBook = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getBookList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_regionListion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_unRegionListion = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
}
public void addBook(Book book) throws android.os.RemoteException;
public java.util.List<Book> getBookList() throws android.os.RemoteException;
public void regionListion(com.android.work.ipc.aidl.IServiceCallBack callBack) throws android.os.RemoteException;
public void unRegionListion(com.android.work.ipc.aidl.IServiceCallBack callBack) throws android.os.RemoteException;
}
