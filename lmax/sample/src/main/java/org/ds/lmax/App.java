package org.ds.lmax;


import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.concurrent.ThreadFactory;

public class App {

    public static class ValueEvent {
        private int value;
        public final static EventFactory EVENT_FACTORY
                = () -> new ValueEvent();

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static class SingleEventPrintConsumer {


        public EventHandler<ValueEvent>[] getEventHandler() {
            EventHandler<ValueEvent> eventHandler
                    = (event, sequence, endOfBatch)
                    -> print(event.getValue(), sequence);
            return new EventHandler[] { eventHandler };
        }

        private void print(int id, long sequenceId) {
           System.out.println("Id is " + id
                    + " sequence id that was used is " + sequenceId);
        }
    }

    public static void main(String[] args) {

        ThreadFactory threadFactory = DaemonThreadFactory.INSTANCE;

        WaitStrategy waitStrategy = new BusySpinWaitStrategy();
        Disruptor<ValueEvent> disruptor
                = new Disruptor<>(
                ValueEvent.EVENT_FACTORY,
                16,
                threadFactory,
                ProducerType.SINGLE,
                waitStrategy);
        disruptor.handleEventsWith(new SingleEventPrintConsumer().getEventHandler());

        RingBuffer<ValueEvent> ringBuffer = disruptor.start();

        for (int eventCount = 0; eventCount < 32; eventCount++) {
            long sequenceId = ringBuffer.next();
            ValueEvent valueEvent = ringBuffer.get(sequenceId);
            valueEvent.setValue(eventCount);
            ringBuffer.publish(sequenceId);
        }

    }


}
