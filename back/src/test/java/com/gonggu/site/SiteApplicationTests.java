package com.gonggu.site;

import com.gonggu.site.board.controller.BoardController;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.repository.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@SpringBootTest
@WebAppConfiguration
class SiteApplicationTests {

	@Autowired
	BoardController boardController;


}
