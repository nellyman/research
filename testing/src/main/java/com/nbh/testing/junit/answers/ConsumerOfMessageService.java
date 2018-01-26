package com.nbh.testing.junit.answers;

/**
 * Class that uses MessageService.
 */
public class ConsumerOfMessageService {

    private MessageService messageService;

    public ConsumerOfMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    public String processAction(ActionEnum action){
        return messageService.getMessage(action);
    }
}
