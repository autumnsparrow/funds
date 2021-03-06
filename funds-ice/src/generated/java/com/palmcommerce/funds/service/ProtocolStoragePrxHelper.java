// **********************************************************************
//
// Copyright (c) 2003-2013 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.5.0
//
// <auto-generated>
//
// Generated from file `funds.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.palmcommerce.funds.service;

public final class ProtocolStoragePrxHelper extends Ice.ObjectPrxHelperBase implements ProtocolStoragePrx
{
    private static final String __save_name = "save";

    public boolean save(String global, String transcode, String fromcode, String tocode, String request, String response)
        throws ProtocolStorageException
    {
        return save(global, transcode, fromcode, tocode, request, response, null, false);
    }

    public boolean save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx)
        throws ProtocolStorageException
    {
        return save(global, transcode, fromcode, tocode, request, response, __ctx, true);
    }

    private boolean save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx, boolean __explicitCtx)
        throws ProtocolStorageException
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "save", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("save");
                    __delBase = __getDelegate(false);
                    _ProtocolStorageDel __del = (_ProtocolStorageDel)__delBase;
                    return __del.save(global, transcode, fromcode, tocode, request, response, __ctx, __observer);
                }
                catch(IceInternal.LocalExceptionWrapper __ex)
                {
                    __handleExceptionWrapper(__delBase, __ex, __observer);
                }
                catch(Ice.LocalException __ex)
                {
                    __cnt = __handleException(__delBase, __ex, null, __cnt, __observer);
                }
            }
        }
        finally
        {
            if(__observer != null)
            {
                __observer.detach();
            }
        }
    }

    public Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response)
    {
        return begin_save(global, transcode, fromcode, tocode, request, response, null, false, null);
    }

    public Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx)
    {
        return begin_save(global, transcode, fromcode, tocode, request, response, __ctx, true, null);
    }

    public Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response, Ice.Callback __cb)
    {
        return begin_save(global, transcode, fromcode, tocode, request, response, null, false, __cb);
    }

    public Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_save(global, transcode, fromcode, tocode, request, response, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response, Callback_ProtocolStorage_save __cb)
    {
        return begin_save(global, transcode, fromcode, tocode, request, response, null, false, __cb);
    }

    public Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx, Callback_ProtocolStorage_save __cb)
    {
        return begin_save(global, transcode, fromcode, tocode, request, response, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__save_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __save_name, __cb);
        try
        {
            __result.__prepare(__save_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(global);
            __os.writeString(transcode);
            __os.writeString(fromcode);
            __os.writeString(tocode);
            __os.writeString(request);
            __os.writeString(response);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public boolean end_save(Ice.AsyncResult __result)
        throws ProtocolStorageException
    {
        Ice.AsyncResult.__check(__result, this, __save_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(ProtocolStorageException __ex)
                {
                    throw __ex;
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            boolean __ret;
            __ret = __is.readBool();
            __result.__endReadParams();
            return __ret;
        }
        catch(Ice.LocalException ex)
        {
            Ice.Instrumentation.InvocationObserver __obsv = __result.__getObserver();
            if(__obsv != null)
            {
                __obsv.failed(ex.ice_name());
            }
            throw ex;
        }
    }

    public static ProtocolStoragePrx checkedCast(Ice.ObjectPrx __obj)
    {
        ProtocolStoragePrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ProtocolStoragePrx)
            {
                __d = (ProtocolStoragePrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    ProtocolStoragePrxHelper __h = new ProtocolStoragePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ProtocolStoragePrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        ProtocolStoragePrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ProtocolStoragePrx)
            {
                __d = (ProtocolStoragePrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    ProtocolStoragePrxHelper __h = new ProtocolStoragePrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ProtocolStoragePrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ProtocolStoragePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    ProtocolStoragePrxHelper __h = new ProtocolStoragePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ProtocolStoragePrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        ProtocolStoragePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    ProtocolStoragePrxHelper __h = new ProtocolStoragePrxHelper();
                    __h.__copyFrom(__bb);
                    __d = __h;
                }
            }
            catch(Ice.FacetNotExistException ex)
            {
            }
        }
        return __d;
    }

    public static ProtocolStoragePrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        ProtocolStoragePrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ProtocolStoragePrx)
            {
                __d = (ProtocolStoragePrx)__obj;
            }
            else
            {
                ProtocolStoragePrxHelper __h = new ProtocolStoragePrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static ProtocolStoragePrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ProtocolStoragePrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            ProtocolStoragePrxHelper __h = new ProtocolStoragePrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::com::palmcommerce::funds::service::ProtocolStorage"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    protected Ice._ObjectDelM __createDelegateM()
    {
        return new _ProtocolStorageDelM();
    }

    protected Ice._ObjectDelD __createDelegateD()
    {
        return new _ProtocolStorageDelD();
    }

    public static void __write(IceInternal.BasicStream __os, ProtocolStoragePrx v)
    {
        __os.writeProxy(v);
    }

    public static ProtocolStoragePrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ProtocolStoragePrxHelper result = new ProtocolStoragePrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}
