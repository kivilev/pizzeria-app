package org.kivilev.pizzeriaapp.component

import com.fasterxml.jackson.databind.ObjectMapper
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings
import org.kivilev.pizzeriaapp.repository.CustomerRepository
import org.kivilev.pizzeriaapp.repository.OrderRepository
import org.kivilev.pizzeriaapp.repository.ToppingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import java.time.Clock

@SpringBootTest
@AutoConfigureMockMvc
@SuppressFBWarnings
class ComponentTestBase {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Autowired
    lateinit var toppingRepository: ToppingRepository

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var clock: Clock
}
