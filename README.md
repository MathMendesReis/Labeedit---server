<h1 align="center" style="font-weight: bold;">Labeedit - server</h1>

<p align="center">
  <a href="#diagrama">Diagrama Entidade-Relacionamento (ER) 📚</a><br>
  <a href="#rodar-projeto">Como rodar o projeto </a><br>
  
</p>


<h2 id="diagrama" align="center" >Diagrama do Banco de Dados</h2>

<div align="center" >
  
[![Diagrama ER](https://mermaid.ink/img/pako:eNqdVO1u2jAUfRXLUqVOoogQCF2k_aggSIiVIgKaNEVCXnIHVomNHIeVAg-zZ9mLzXGIYSH9Wv7E9_r4nBPfm7vDIY8AuxhEj5KFIHHAkHruxuP5zPcmPtrlieyZzQY9RCM0Hp5yvhSULRAjMVwkISZ0dcr2iAQUClCv6E6W8uk6Uq8ifQhYvph-87zpe02EnElg8m1FzZEmIAYR6g9Lkl8HQ8-fP0zmvYGv1xfqpZMmL38ByNLG0ZryEErKWUmr-3B_743-_wM_bKfqRoybU9H3-5sbvi9u30UB7gpKAvwCznyGRvJYeTTgI0cVcgrxi5SXVdDcqZBUIJ6iHiShDqp1qo9PIIQfUJx4R7lL1VMUK_oIaI8imhxXjDPDaG7y6gqNuCQJgqf1ioZE0g1JXLM3HqIvaCxoTMQWDWGLrrtLsgGd-vNb3fQnA-1n0D4XQBfsHOolUhC2ACrOwBNYkVD5JFkFuKmai2ax6pI0o-aq-pSgjV4neY8k6NpyR6_RFCX9l2mtRofqSb0nPkipa6_pND7nknCiyXmPwVv-qtqiyqvBRQXuwvbItV5RKjqo7Fz9gjl7xM_po0z4pFG4MTK4hmMQakxGagTrvguwXEKsFQIcEfGY9dZB4Ugqub9lIXalSKGGBU8XS-z-JKtERfn4PI5wk10T9p3zuDiiQuzu8BN2Lcupt9pO83PDbltW22m3aniL3RunVVfBbcdu3tpNRwEONfysGay67TRbVtu27E6z0WnY1uEv627rOA?type=png)](https://mermaid.live/edit#pako:eNqdVO1u2jAUfRXLUqVOoogQCF2k_aggSIiVIgKaNEVCXnIHVomNHIeVAg-zZ9mLzXGIYSH9Wv7E9_r4nBPfm7vDIY8AuxhEj5KFIHHAkHruxuP5zPcmPtrlieyZzQY9RCM0Hp5yvhSULRAjMVwkISZ0dcr2iAQUClCv6E6W8uk6Uq8ifQhYvph-87zpe02EnElg8m1FzZEmIAYR6g9Lkl8HQ8-fP0zmvYGv1xfqpZMmL38ByNLG0ZryEErKWUmr-3B_743-_wM_bKfqRoybU9H3-5sbvi9u30UB7gpKAvwCznyGRvJYeTTgI0cVcgrxi5SXVdDcqZBUIJ6iHiShDqp1qo9PIIQfUJx4R7lL1VMUK_oIaI8imhxXjDPDaG7y6gqNuCQJgqf1ioZE0g1JXLM3HqIvaCxoTMQWDWGLrrtLsgGd-vNb3fQnA-1n0D4XQBfsHOolUhC2ACrOwBNYkVD5JFkFuKmai2ax6pI0o-aq-pSgjV4neY8k6NpyR6_RFCX9l2mtRofqSb0nPkipa6_pND7nknCiyXmPwVv-qtqiyqvBRQXuwvbItV5RKjqo7Fz9gjl7xM_po0z4pFG4MTK4hmMQakxGagTrvguwXEKsFQIcEfGY9dZB4Ugqub9lIXalSKGGBU8XS-z-JKtERfn4PI5wk10T9p3zuDiiQuzu8BN2Lcupt9pO83PDbltW22m3aniL3RunVVfBbcdu3tpNRwEONfysGay67TRbVtu27E6z0WnY1uEv627rOA)

</div>

<h2 id="rodar-projeto" align="center" >Como rodar o projeto?</h2>

<h3>Com docker: </h3>

<p>O projeto foi configurado para rodar usando docker </p>

<ul>
  <li>
    <p>Gerar pacote java</p>
      <pre>
        <code>
          mvn clean package -Dmaven.test.skip=true
      </code>
    </pre>
  </li>
  <li>
    <p>Gerar imagem docker</p>
      <pre>
        <code>
          docker build -t api-labeedit .
      </code>
    </pre>
  </li>
  <li>
    <p>Rodar o docker composer</p>
      <pre>
        <code>
          docker compose up -d
      </code>
    </pre>
  </li>
</ul>

<p>Após alguns segundos, a documentação ja estaŕa disponivel na porta:
  <pre>
    <code>
      http://localhost:8080/swagger-ui/index.html
    </code>
  </pre>
</p>
