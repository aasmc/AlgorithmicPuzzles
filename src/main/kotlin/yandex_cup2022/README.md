Задана строка
s
из заглавных латинских букв, запакованная в Run-Length Endoding: каждый сегмент подряд идущих одинаковых букв (но не меньше двух) заменяется на свою длину (в десятичном виде) и букву.
Например, строка «AABBACFFF» будет представлена как «2A2BAC3F». Выполните циклический сдвиг
s
на
k
символов влево.

Формат ввода
В первой строке два целых числа
n
,
k
(
1
≤
n
≤
1
0
5
;
1
≤
k
≤
1
0
1
8
) — длина шифра и требуемый сдвиг.
Во второй строке задана закодированная
s
. Длины сегментов одинаковых букв не превосходят
1
0
1
8
.

Формат вывода
Строка
s
после циклического сдвига, также в RLE формате.