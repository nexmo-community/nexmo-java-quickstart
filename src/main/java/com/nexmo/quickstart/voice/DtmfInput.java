/*
 * Copyright (c) 2011-2017 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.nexmo.quickstart.voice;

import com.nexmo.client.NexmoUnexpectedException;
import com.nexmo.client.incoming.DtmfOutput;
import com.nexmo.client.incoming.InputEvent;
import com.nexmo.client.voice.ncco.*;
import spark.Route;
import spark.Spark;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class DtmfInput {
    public static void main(String[] args) {
        /*
         * Route to answer incoming calls.
         */
        Route answerRoute = (req, res) -> {
            TalkAction intro = TalkAction
                    .builder("Hello please press any key to continue")
                    .build();

            DTMFSettings dtmfSettings = new DTMFSettings();
            dtmfSettings.setMaxDigits(3);
            dtmfSettings.setTimeOut(2);

            InputAction input = InputAction.builder()
                    .eventUrl(String.format("%s://%s/webhooks/dtmf", req.scheme(), req.host()))
                    .dtmf(dtmfSettings)
                    .build();


            res.type("application/json");

            return new Ncco(intro, input).toJson();
        };

        /*
         * Route which returns NCCO saying which DTMF code was received.
         */
        Route inputRoute = (req, res) -> {
            System.out.println("req: " + req.body());

            InputEvent<DtmfOutput> event;
            try {
                event = InputEvent.fromJson(req.body());
            }catch (NexmoUnexpectedException e){
                res.body(e.getLocalizedMessage());
                return e.getLocalizedMessage();
            }

            TalkAction response = TalkAction.builder(String.format("You pressed %s, Goodbye.",
                    event.getDtmf().getDigits()
            )).build();

            res.type("application/json");

            return new Ncco(response).toJson();
        };

        Spark.port(3000);
        Spark.get("/webhooks/answer", answerRoute);
        Spark.post("/webhooks/dtmf", inputRoute);
    }
}
