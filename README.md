# TWS PERCENT

Widget Android para acompanhar a bateria disponível dos fones Bluetooth diretamente na tela inicial, com a identidade visual da artista **DVORAH**.

> Projeto experimental/MVP criado para uso pessoal e evolução futura.

## O que o app faz

- Exibe a porcentagem de bateria dos fones em um widget inicial.
- Começa em 1×1 e permite redimensionamento pelo launcher.
- Atualiza automaticamente em aproximadamente 5 minutos.
- Permite atualização imediata ao tocar no widget.
- Usa cores de alerta no percentual:
  - Verde: 70%–100%
  - Amarelo: 30%–69%
  - Vermelho: 0%–29%
  - Branco: nível indisponível
- Mostra a abelha da marca como fundo do widget.
- Possui bordas arredondadas e percentual adaptável ao tamanho do widget.
- Inclui uma tela interna com a identidade visual da Dvorah, permissão Bluetooth e atalhos para Linktree e Youtube.

## Demonstração visual

A tela interna usa uma arte vertical de fundo com estética industrial, alto contraste, textura desgastada e detalhes em ferrugem. Os botões visuais são **Linktree** e **Youtube**.

## Permissões

No Android 12 ou superior, o app solicita apenas as permissões necessárias para consultar dispositivos Bluetooth próximos:

- `BLUETOOTH_CONNECT`
- `BLUETOOTH_SCAN`

O app não solicita localização, câmera, microfone, contatos ou acesso a arquivos pessoais.

## Como testar

### Pelo Android Studio

1. Clone ou baixe este repositório.
2. Abra a pasta no Android Studio.
3. Aguarde a sincronização do Gradle.
4. Execute o app em um aparelho Android ou emulador.
5. Conceda a permissão Bluetooth quando solicitada.
6. Abra o menu de widgets do Android e adicione **TWS PERCENT** à tela inicial.

### Instalando um APK de teste

Os APKs de teste não são versionados no Git. Para gerar um APK localmente:

```bash
./gradlew assembleDebug
```

O arquivo será criado em:

```text
app/build/outputs/apk/debug/app-debug.apk
```

## Limitações conhecidas

O Android não oferece uma API pública uniforme para o nível de bateria de todos os fones. Este MVP lê o nível exposto pelo dispositivo Bluetooth pareado através da API disponível no aparelho. Dependendo do modelo, pode aparecer apenas a bateria do estojo, apenas um lado, ou nenhum nível.

O Android também pode atrasar atualizações em modo de economia de bateria, Doze ou por restrições do fabricante.

## Próximos passos possíveis

- Implementar leitura GATT do Battery Service.
- Adicionar suporte específico para diferentes fabricantes.
- Mostrar bateria esquerda, direita e estojo separadamente.
- Criar uma versão assinada para publicação na Google Play.
- Adicionar testes automatizados e uma tela de configurações.

## Estrutura principal

```text
app/src/main/java/              Código da Activity e do widget
app/src/main/res/layout/        Layouts da aplicação e do widget
app/src/main/res/drawable/      Ícones e fundos vetoriais
app/src/main/res/drawable-nodpi/Artes visuais sem escala automática
app/src/main/res/xml/           Configuração do App Widget
```

## Identidade visual

O nome do app é **TWS PERCENT**. A tela interna utiliza a identidade da Dvorah e os links oficiais:

- Linktree: https://linktr.ee/dvorah.ofc
- Youtube: https://youtube.com/@luke.fraktur

## Licença

Este projeto ainda não possui uma licença de código aberto definida. Antes de publicar como open source, escolha uma licença adequada, como MIT, Apache-2.0 ou GPL-3.0.
