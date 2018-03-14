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

public final class ProtocolProcessorPrxHelper extends Ice.ObjectPrxHelperBase implements ProtocolProcessorPrx
{
    private static final String __doProcess_name = "doProcess";

    public boolean doProcess(String transcode, String fromcode, String tocode, String request, Ice.StringHolder response)
        throws ProtocolProcessException
    {
        return doProcess(transcode, fromcode, tocode, request, response, null, false);
    }

    public boolean doProcess(String transcode, String fromcode, String tocode, String request, Ice.StringHolder response, java.util.Map<String, String> __ctx)
        throws ProtocolProcessException
    {
        return doProcess(transcode, fromcode, tocode, request, response, __ctx, true);
    }

    private boolean doProcess(String transcode, String fromcode, String tocode, String request, Ice.StringHolder response, java.util.Map<String, String> __ctx, boolean __explicitCtx)
        throws ProtocolProcessException
    {
        if(__explicitCtx && __ctx == null)
        {
            __ctx = _emptyContext;
        }
        final Ice.Instrumentation.InvocationObserver __observer = IceInternal.ObserverHelper.get(this, "doProcess", __ctx);
        int __cnt = 0;
        try
        {
            while(true)
            {
                Ice._ObjectDel __delBase = null;
                try
                {
                    __checkTwowayOnly("doProcess");
                    __delBase = __getDelegate(false);
                    _ProtocolProcessorDel __del = (_ProtocolProcessorDel)__delBase;
                    return __del.doProcess(transcode, fromcode, tocode, request, response, __ctx, __observer);
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

    public Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request)
    {
        return begin_doProcess(transcode, fromcode, tocode, request, null, false, null);
    }

    public Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request, java.util.Map<String, String> __ctx)
    {
        return begin_doProcess(transcode, fromcode, tocode, request, __ctx, true, null);
    }

    public Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request, Ice.Callback __cb)
    {
        return begin_doProcess(transcode, fromcode, tocode, request, null, false, __cb);
    }

    public Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request, java.util.Map<String, String> __ctx, Ice.Callback __cb)
    {
        return begin_doProcess(transcode, fromcode, tocode, request, __ctx, true, __cb);
    }

    public Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request, Callback_ProtocolProcessor_doProcess __cb)
    {
        return begin_doProcess(transcode, fromcode, tocode, request, null, false, __cb);
    }

    public Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request, java.util.Map<String, String> __ctx, Callback_ProtocolProcessor_doProcess __cb)
    {
        return begin_doProcess(transcode, fromcode, tocode, request, __ctx, true, __cb);
    }

    private Ice.AsyncResult begin_doProcess(String transcode, String fromcode, String tocode, String request, java.util.Map<String, String> __ctx, boolean __explicitCtx, IceInternal.CallbackBase __cb)
    {
        __checkAsyncTwowayOnly(__doProcess_name);
        IceInternal.OutgoingAsync __result = new IceInternal.OutgoingAsync(this, __doProcess_name, __cb);
        try
        {
            __result.__prepare(__doProcess_name, Ice.OperationMode.Normal, __ctx, __explicitCtx);
            IceInternal.BasicStream __os = __result.__startWriteParams(Ice.FormatType.DefaultFormat);
            __os.writeString(transcode);
            __os.writeString(fromcode);
            __os.writeString(tocode);
            __os.writeString(request);
            __result.__endWriteParams();
            __result.__send(true);
        }
        catch(Ice.LocalException __ex)
        {
            __result.__exceptionAsync(__ex);
        }
        return __result;
    }

    public boolean end_doProcess(Ice.StringHolder response, Ice.AsyncResult __result)
        throws ProtocolProcessException
    {
        Ice.AsyncResult.__check(__result, this, __doProcess_name);
        boolean __ok = __result.__wait();
        try
        {
            if(!__ok)
            {
                try
                {
                    __result.__throwUserException();
                }
                catch(ProtocolProcessException __ex)
                {
                    throw __ex;
                }
                catch(Ice.UserException __ex)
                {
                    throw new Ice.UnknownUserException(__ex.ice_name(), __ex);
                }
            }
            IceInternal.BasicStream __is = __result.__startReadParams();
            response.value = __is.readString();
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

    public static ProtocolProcessorPrx checkedCast(Ice.ObjectPrx __obj)
    {
        ProtocolProcessorPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ProtocolProcessorPrx)
            {
                __d = (ProtocolProcessorPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId()))
                {
                    ProtocolProcessorPrxHelper __h = new ProtocolProcessorPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ProtocolProcessorPrx checkedCast(Ice.ObjectPrx __obj, java.util.Map<String, String> __ctx)
    {
        ProtocolProcessorPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ProtocolProcessorPrx)
            {
                __d = (ProtocolProcessorPrx)__obj;
            }
            else
            {
                if(__obj.ice_isA(ice_staticId(), __ctx))
                {
                    ProtocolProcessorPrxHelper __h = new ProtocolProcessorPrxHelper();
                    __h.__copyFrom(__obj);
                    __d = __h;
                }
            }
        }
        return __d;
    }

    public static ProtocolProcessorPrx checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ProtocolProcessorPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId()))
                {
                    ProtocolProcessorPrxHelper __h = new ProtocolProcessorPrxHelper();
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

    public static ProtocolProcessorPrx checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map<String, String> __ctx)
    {
        ProtocolProcessorPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            try
            {
                if(__bb.ice_isA(ice_staticId(), __ctx))
                {
                    ProtocolProcessorPrxHelper __h = new ProtocolProcessorPrxHelper();
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

    public static ProtocolProcessorPrx uncheckedCast(Ice.ObjectPrx __obj)
    {
        ProtocolProcessorPrx __d = null;
        if(__obj != null)
        {
            if(__obj instanceof ProtocolProcessorPrx)
            {
                __d = (ProtocolProcessorPrx)__obj;
            }
            else
            {
                ProtocolProcessorPrxHelper __h = new ProtocolProcessorPrxHelper();
                __h.__copyFrom(__obj);
                __d = __h;
            }
        }
        return __d;
    }

    public static ProtocolProcessorPrx uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
        ProtocolProcessorPrx __d = null;
        if(__obj != null)
        {
            Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
            ProtocolProcessorPrxHelper __h = new ProtocolProcessorPrxHelper();
            __h.__copyFrom(__bb);
            __d = __h;
        }
        return __d;
    }

    public static final String[] __ids =
    {
        "::Ice::Object",
        "::com::palmcommerce::funds::service::ProtocolProcessor"
    };

    public static String ice_staticId()
    {
        return __ids[1];
    }

    protected Ice._ObjectDelM __createDelegateM()
    {
        return new _ProtocolProcessorDelM();
    }

    protected Ice._ObjectDelD __createDelegateD()
    {
        return new _ProtocolProcessorDelD();
    }

    public static void __write(IceInternal.BasicStream __os, ProtocolProcessorPrx v)
    {
        __os.writeProxy(v);
    }

    public static ProtocolProcessorPrx __read(IceInternal.BasicStream __is)
    {
        Ice.ObjectPrx proxy = __is.readProxy();
        if(proxy != null)
        {
            ProtocolProcessorPrxHelper result = new ProtocolProcessorPrxHelper();
            result.__copyFrom(proxy);
            return result;
        }
        return null;
    }

    public static final long serialVersionUID = 0L;
}