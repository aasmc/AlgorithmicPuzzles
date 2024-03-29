В компании, где работает Иван, активно применяют различные средства для контроля качества кода.

Перед тем, как использовать написанную программу, ее код проверяют при помощи ﻿
n
n﻿ проверок. В качестве результата каждой из проверок дается некоторое целое число — опасность кода. Назовем общей опасностью кода произведение опасностей для каждой из проверок.

Ваня только что дописал свою программу и запустил все проверки. В результате проверок, он получил массив опасностей ﻿
a
1
,
a
2
,
…
,
a
n
a
1
​
,a
2
​
,…,a
n
​
﻿.

Иван хочет минимизировать общую опасность написанного кода, для этого он готов не более ﻿
c
c﻿ раз внести изменения в свой код. За одно внесение изменений он может увеличить или уменьшить на ﻿
d
d﻿ результат одной из проверок, результат остальных проверок при этом не поменяется.

Помогите Ивану и предложите значения результатов проверок, дающие минимальную общую опасность, которые он может получить, сделав не более ﻿
c
c﻿ изменений.

Формат входных данных

В первой строке ввода дано три целых числа ﻿
n
n﻿, ﻿
c
c﻿ и ﻿
d
d﻿ — количество проверок, максимальное количество изменений, которое готов внести Иван, и число ﻿
d
d﻿ ﻿
(
1
≤
n
≤
200000
;
1
≤
c
≤
200000
;
1
≤
d
≤
1
0
9
)
(1≤n≤200000;1≤c≤200000;1≤d≤10
9
)﻿.

Во второй строке ввода дано ﻿
n
n﻿ целых чисел ﻿
a
1
,
a
2
,
…
,
a
n
a
1
​
,a
2
​
,…,a
n
​
﻿ — результаты проверок кода Ивана до всех изменений ﻿
(
−
1
0
9
≤
a
i
≤
1
0
9
)
(−10
9
≤a
i
​
≤10
9
)﻿.

Формат выходных данных

В единственной строке выведите ﻿
n
n﻿ значений результатов проверок кода после применения не более чем ﻿
c
c﻿ исправлений. Произведение всех результатов проверок должно быть минимальным.

Если ответов несколько, вы можете вывести любой.

```text
5 3 1
5 4 3 5 2

5 4 3 5 -1
```

```text
5 3 1
5 4 3 5 5 

5 4 0 5 5
```

```text
5 3 1
5 4 4 5 5 

5 1 4 5 5
```

```text
3 2 7
5 4 2

5 11 -5
```