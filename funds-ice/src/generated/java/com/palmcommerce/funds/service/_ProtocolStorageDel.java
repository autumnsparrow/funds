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

public interface _ProtocolStorageDel extends Ice._ObjectDel
{
    boolean save(String global, String transcode, String fromcode, String tocode, String request, String response, java.util.Map<String, String> __ctx, Ice.Instrumentation.InvocationObserver __obsv)
        throws IceInternal.LocalExceptionWrapper,
               ProtocolStorageException;
}
