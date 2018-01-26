package com.nbh.testing.junit.answers;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * A contrived Test using an Answer on the  Mock.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleOfAnswerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private ConsumerOfMessageService consumerOfMessageService;

    @Test
    public void shouldAnswerCorrectly(){

        Mockito.when(messageService.getMessage(Mockito.any(ActionEnum.class))).thenAnswer(new MessageServiceAnswers());//consumerOfMessageService.processAction()

        String msg = consumerOfMessageService.processAction(ActionEnum.ACTION_ONE);

        MatcherAssert.assertThat(msg, CoreMatchers.is("one"));

        msg = consumerOfMessageService.processAction(ActionEnum.ACTION_TWO);

        MatcherAssert.assertThat(msg, CoreMatchers.is("two"));

    }


    /**Based on the arguments from the code, determines the response from the mocked method**/
    class MessageServiceAnswers implements org.mockito.stubbing.Answer<String>{

        @Override
        public String answer(InvocationOnMock invocation) throws Throwable {
            Object[] args = invocation.getArguments();
            // if endpoint argument is room then send back room Json
            if (ActionEnum.ACTION_ONE.equals(args[0])){
                return "one";
            }
            if (ActionEnum.ACTION_TWO.equals(args[0])){
                return "two";
            }
                return "three";
        }
    }
}