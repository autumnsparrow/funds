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

public interface _ProtocolProcessorOperationsNC
{
    boolean doProcess(String transcode, String fromcode, String tocode, String request, Ice.StringHolder response)
        throws ProtocolProcessException;
}
