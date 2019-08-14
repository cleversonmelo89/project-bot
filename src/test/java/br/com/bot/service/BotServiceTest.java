package br.com.bot.service;

import static org.junit.Assert.assertEquals;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import br.com.bot.converter.BotRequestConverter;
import br.com.bot.converter.BotResponseConverter;
import br.com.bot.domain.Bot;
import br.com.bot.json.BotJson;
import br.com.bot.repository.BotRepository;
import br.com.bot.service.BotService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BotServiceTest {

	@Autowired
	private BotService botService;

	@MockBean
	private BotRepository botRepository;

	@MockBean
	private BotRequestConverter botConverter;

	@MockBean
	private BotResponseConverter botResponseConverter;

	@Test
	public void testSaveBotSuccess() {

		Bot bot = getBot();
		BotJson botJson = getBotJson();
		Mockito.when(botRepository.findBotById("16edd3b3-3f75-40df-af07-2a3813a79ce9")).thenReturn(null);
		Mockito.when(botConverter.convert(botJson)).thenReturn(bot);
		Mockito.when(botRepository.save(bot)).thenReturn(bot);
		Mockito.when(botResponseConverter.convert(bot)).thenReturn(botJson);

		BotJson returnBotJson = botService.createBot(botJson);
		assertEquals(botJson.getId(), returnBotJson.getId());
		assertEquals(botJson.getName(), returnBotJson.getName());
	}

	@Test(expected = ResponseStatusException.class)
	public void testSaveBotConflict() {

		Bot bot = getBot();
		BotJson botJson = getBotJson();
		Mockito.when(botRepository.findBotById("16edd3b3-3f75-40df-af07-2a3813a79ce9")).thenReturn(bot);

		botService.createBot(botJson);
	}

	@Test
	public void testeGetBotByIdSuccess() {
		Bot bot = getBot();
		BotJson botJson = getBotJson();
		Mockito.when(botRepository.findBotById("16edd3b3-3f75-40df-af07-2a3813a79ce9")).thenReturn(bot);
		Mockito.when(botResponseConverter.convert(bot)).thenReturn(botJson);
		
		BotJson returnBotJson = botService.getBotId("16edd3b3-3f75-40df-af07-2a3813a79ce9");
		assertEquals(botJson.getId(), returnBotJson.getId());
		assertEquals(botJson.getName(), returnBotJson.getName());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void testeGetBotByIdNotFound() {
		Mockito.when(botRepository.findBotById("16edd3b3-3f75-40df-af07-2a3813a79ce9")).thenReturn(null);
		
		botService.getBotId("16edd3b3-3f75-40df-af07-2a3813a79ce9");
	}

	private BotJson getBotJson() {
		return BotJson.builder().id("16edd3b3-3f75-40df-af07-2a3813a79ce9").name("Marley").build();
	}

	private Bot getBot() {
		return Bot.builder()._id(ObjectId.get()).id("16edd3b3-3f75-40df-af07-2a3813a79ce9").name("Marley").build();
	}
}
