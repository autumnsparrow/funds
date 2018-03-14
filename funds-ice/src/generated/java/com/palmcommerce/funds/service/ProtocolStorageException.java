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

public class ProtocolStorageException extends Ice.UserException
{
    public ProtocolStorageException()
    {
    }

    public ProtocolStorageException(Throwable __cause)
    {
        super(__cause);
    }

    public ProtocolStorageException(String state, String reason)
    {
        this.state = state;
        this.reason = reason;
    }

    public ProtocolStorageException(String state, String reason, Throwable __cause)
    {
        super(__cause);
        this.state = state;
        this.reason = reason;
    }

    public String
    ice_name()
    {
        return "com::palmcommerce::funds::service::ProtocolStorageException";
    }

    public String state;

    public String reason;

    protected void
    __writeImpl(IceInternal.BasicStream __os)
    {
        __os.startWriteSlice("::com::palmcommerce::funds::service::ProtocolStorageException", -1, true);
        __os.writeString(state);
        __os.writeString(reason);
        __os.endWriteSlice();
    }

    protected void
    __readImpl(IceInternal.BasicStream __is)
    {
        __is.startReadSlice();
        state = __is.readString();
        reason = __is.readString();
        __is.endReadSlice();
    }

    public static final long serialVersionUID = -6155949661502292485L;
}
