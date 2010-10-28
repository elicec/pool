/*
 * Copyright (C) 2006 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.telephony.gsm.stk;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Class for representing "InterfaceTransportLevel" object for STK.
 *
 * {@hide}
 */
public class InterfaceTransportLevel implements Parcelable {
    public int port;
    public TransportProtocol protocol;

    public enum TransportProtocol {
        RESERVED(0x00),
        UDP_CLIENT_REMOTE(0x01),
        TCP_CLIENT_REMOTE(0x02),
        TCP_SERVER(0x03),
        UDP_CLIENT_LOCAL(0x04),
        TCP_CLIENT_LOCAL(0x05);

        private int mValue;

        TransportProtocol(int value) {
            mValue = value;
        }

        public int value() {
            return mValue;
        }
    }

    public InterfaceTransportLevel(int port, TransportProtocol protocol) {
        this.port = port;
        this.protocol = protocol;
    }

    private InterfaceTransportLevel(Parcel in) {
        port = in.readInt();
        protocol = TransportProtocol.values()[in.readInt()];
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(port);
        dest.writeInt(protocol.ordinal());
    }

    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<InterfaceTransportLevel> CREATOR = new Parcelable.Creator<InterfaceTransportLevel>() {
        public InterfaceTransportLevel createFromParcel(Parcel in) {
            return new InterfaceTransportLevel(in);
        }

        public InterfaceTransportLevel[] newArray(int size) {
            return new InterfaceTransportLevel[size];
        }
    };
}