-- ========================
-- TẠO ASSIGNMENT MỚI
-- ========================
INSERT INTO assignment (title, description, assignment_type, start_time, end_time, duration, repeat_limit, created_at)
VALUES
('IELTS Advanced Vocabulary Test', 'Vocabulary and usage for IELTS 8.0+ level', 'quiz', NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), 120, 1, NOW());

-- Lấy ID của assignment vừa tạo
SET @assignment_id = LAST_INSERT_ID();

-- ========================
-- TẠO QUESTION MATERIAL CHO 40 CÂU
-- ========================
INSERT INTO question_material (type, url)
VALUES 
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL),
('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL), ('text', NULL);

-- ========================
-- TẠO ASSIGNMENT SECTION CHO 40 CÂU (1 section)
-- ========================
INSERT INTO assignment_section (name, question_type, assignment_id)
VALUES ('IELTS Vocabulary Section - Part 1', 'mcq', @assignment_id);
INSERT INTO assignment_section (name, question_type, assignment_id)
VALUES ('IELTS Vocabulary Section - Part 2', 'blank', @assignment_id);

-- ========================
-- TẠO 40 QUESTION
-- ========================
-- Lưu ý: gán question_material_id và assignment_section_id
-- Giả sử assignment_section_id vừa tạo là 100 (bạn có thể lấy LAST_INSERT_ID() nếu cần)
-- Thay 100 bằng giá trị thực tế của assignment_section_id
SET @section_id = 21;
SET @section_id_blank = 22;

INSERT INTO question (assignment_section_id, question_material_id, question_content, type, question_explain)
VALUES
(@section_id, 21, 'Choose the correct synonym for "alleviate"', 'mcq', 'To reduce or make less severe'),
(@section_id, 21, 'Choose the correct antonym for "benevolent"', 'mcq', 'Opposite of kind and generous'),
(@section_id, 21, 'Select the word that best fits the context: "He showed remarkable ___ in negotiations."', 'mcq', 'Skill and diplomacy'),
(@section_id, 21, 'Which word is closest in meaning to "candid"?', 'mcq', 'Truthful and straightforward'),
(@section_id, 21, 'Choose the correct usage of "detrimental" in a sentence', 'mcq', 'Harmful or damaging'),
(@section_id, 21, 'Select the synonym for "elucidate"', 'mcq', 'To make clear or explain'),
(@section_id, 21, 'Choose the correct meaning of "fallacious"', 'mcq', 'Based on mistaken belief'),
(@section_id, 21, 'Identify the word that best completes: "Her actions were ___ to the cause."', 'mcq', 'Helpful and supportive'),
(@section_id, 21, 'Select the word that is opposite of "garrulous"', 'mcq', 'Talkative'),
(@section_id, 21, 'Choose the correct usage of "harbinger"', 'mcq', 'Something that signals a future event'),
(@section_id, 21, 'Pick the synonym of "impeccable"', 'mcq', 'Flawless or perfect'),
(@section_id, 21, 'Choose the correct antonym of "jubilant"', 'mcq', 'Extremely happy'),
(@section_id, 21, 'Select the word that best fits: "The study was highly ___"', 'mcq', 'Influential or authoritative'),
(@section_id, 14, 'Which word is closest in meaning to "meticulous"?', 'mcq', 'Extremely careful and precise'),
(@section_id, 15, 'Choose the correct usage of "nonchalant"', 'mcq', 'Calm and unconcerned'),
(@section_id, 16, 'Select the synonym for "obfuscate"', 'mcq', 'To make unclear or confusing'),
(@section_id, 17, 'Choose the correct meaning of "palpable"', 'mcq', 'Able to be felt or touched'),
(@section_id, 18, 'Identify the word that best completes: "The plan was ___ in every detail."', 'mcq', 'Thorough or complete'),
(@section_id, 19, 'Select the word opposite of "quintessential"', 'mcq', 'Not representative'),
(@section_id, 20, 'Choose the correct usage of "resilient"', 'mcq', 'Able to recover quickly from difficulties'),
(@section_id, 21, 'Pick the synonym of "scrutinize"', 'mcq', 'Examine closely'),
(@section_id, 22, 'Choose the correct antonym of "tenacious"', 'mcq', 'Weak-willed'),
(@section_id, 23, 'Select the word that best fits: "Her comments were highly ___"', 'mcq', 'Insightful'),
(@section_id, 24, 'Which word is closest in meaning to "ubiquitous"?', 'mcq', 'Present everywhere'),
(@section_id, 25, 'Choose the correct usage of "venerable"', 'mcq', 'Commanding respect due to age or dignity'),
(@section_id, 26, 'Select the synonym for "wary"', 'mcq', 'Cautious or alert'),
(@section_id, 27, 'Choose the correct meaning of "xenophobia"', 'mcq', 'Fear or hatred of foreigners'),
(@section_id, 28, 'Identify the word that best completes: "His speech was ___ and compelling."', 'mcq', 'Persuasive'),
(@section_id, 29, 'Select the word opposite of "yielding"', 'mcq', 'Stubborn or resistant'),
(@section_id, 30, 'Choose the correct usage of "zealous"', 'mcq', 'Full of enthusiasm'),
(@section_id, 31, 'Pick the synonym of "aberration"', 'mcq', 'A deviation from the norm'),
(@section_id, 32, 'Choose the correct antonym of "belligerent"', 'mcq', 'Peaceful'),
(@section_id, 33, 'Select the word that best fits: "The results were ___"', 'mcq', 'Unexpected or unusual'),
(@section_id, 34, 'Which word is closest in meaning to "concur"?', 'mcq', 'Agree'),
(@section_id, 35, 'Choose the correct usage of "diligent"', 'mcq', 'Hardworking and careful'),
(@section_id, 36, 'Select the synonym for "emulate"', 'mcq', 'To imitate or copy'),
(@section_id, 37, 'Choose the correct meaning of "fortuitous"', 'mcq', 'Happening by chance'),
(@section_id, 38, 'Identify the word that best completes: "The team remained ___ despite difficulties."', 'mcq', 'Optimistic or hopeful'),
(@section_id, 39, 'Select the word opposite of "gregarious"', 'mcq', 'Shy or reserved'),
(@section_id, 40, 'Choose the correct usage of "haughty"', 'mcq', 'Arrogant or disdainful'),
(@section_id_blank, 40, 'Fill in the blank: The scientist’s discovery was so {groundbreaking} that it changed the field entirely.', 'blank', 'Think of a word meaning "groundbreaking" or "revolutionary"'),
(@section_id_blank, 40, 'Complete the sentence: Her argument was both logical and {persuasive}.', 'blank', 'Look for a word meaning "convincing" or "persuasive"'),
(@section_id_blank, 40, 'Fill in the blank: Despite the storm, the ship remained {steady} and reached the shore safely.', 'blank', 'Use a word meaning "steady" or "unshaken"'),
(@section_id_blank, null, 'Complete the sentence: The student’s explanation was {lucid} and helped the class understand the concept.', 'blank', 'Think of a word meaning "clear" or "lucid"'),
(@section_id_blank, null, 'Fill in the blank: The politician’s {offensive} remarks caused outrage among the public.', 'blank', 'Use a word meaning "offensive" or "insensitive"'),
(@section_id_blank, null, 'Complete the sentence: The solution was {surprisingly} simple, yet incredibly effective.', 'blank', 'A word meaning "deceptively" or "surprisingly"'),
(@section_id_blank, null, 'Fill in the blank: Over time, the monument became a {enduring} symbol of national pride.', 'blank', 'Use a word meaning "lasting" or "enduring"'),
(@section_id_blank, null, 'Complete the sentence: His actions were {consistent} with his principles.', 'blank', 'Think of a word meaning "consistent" or "aligned"'),
(@section_id_blank, null, 'Fill in the blank: The medicine had an {immediate} effect on the patient’s condition.', 'blank', 'A word meaning "immediate" or "instantaneous"'),
(@section_id_blank, null, 'Complete the sentence: She gave a {detailed} account of the events that transpired.', 'blank', 'Use a word meaning "detailed" or "thorough"');

-- ========================
-- THÊM MCQ ANSWER CHO 40 CÂU (question_id từ 21 đến 60)
-- ========================
INSERT INTO mcq_answer (question_id, answer_content, is_correct) VALUES
-- Q21
(21, 'Ease or relieve', 1),
(21, 'Complicate', 0),
(21, 'Exaggerate', 0),
(21, 'Aggravate', 0),

-- Q22
(22, 'Malevolent', 1),
(22, 'Kind-hearted', 0),
(22, 'Generous', 0),
(22, 'Altruistic', 0),

-- Q23
(23, 'Tact', 1),
(23, 'Ignorance', 0),
(23, 'Folly', 0),
(23, 'Negligence', 0),

-- Q24
(24, 'Frank', 1),
(24, 'Deceptive', 0),
(24, 'Reserved', 0),
(24, 'Secretive', 0),

-- Q25
(25, 'Smoking is detrimental to health.', 1),
(25, 'She is detrimental in her work.', 0),
(25, 'The book was detrimental.', 0),
(25, 'I feel detrimental today.', 0),

-- Q26
(26, 'Clarify', 1),
(26, 'Complicate', 0),
(26, 'Confuse', 0),
(26, 'Ignore', 0),

-- Q27
(27, 'False or misleading', 1),
(27, 'True and logical', 0),
(27, 'Obvious', 0),
(27, 'Accurate', 0),

-- Q28
(28, 'Beneficial', 1),
(28, 'Opposed', 0),
(28, 'Hostile', 0),
(28, 'Contradictory', 0),

-- Q29
(29, 'Taciturn', 1),
(29, 'Verbose', 0),
(29, 'Loquacious', 0),
(29, 'Chatty', 0),

-- Q30
(30, 'A robin is a harbinger of spring.', 1),
(30, 'He was harbinger with his friends.', 0),
(30, 'Harbinger is a sweet fruit.', 0),
(30, 'She felt harbinger today.', 0),

-- Q31
(31, 'Perfect', 1),
(31, 'Ordinary', 0),
(31, 'Mediocre', 0),
(31, 'Flawed', 0),

-- Q32
(32, 'Miserable', 1),
(32, 'Ecstatic', 0),
(32, 'Joyous', 0),
(32, 'Exuberant', 0),

-- Q33
(33, 'Authoritative', 1),
(33, 'Weak', 0),
(33, 'Trivial', 0),
(33, 'Negligible', 0),

-- Q34
(34, 'Careful', 1),
(34, 'Sloppy', 0),
(34, 'Reckless', 0),
(34, 'Lazy', 0),

-- Q35
(35, 'Unconcerned', 1),
(35, 'Anxious', 0),
(35, 'Worried', 0),
(35, 'Panicked', 0),

-- Q36
(36, 'Confuse', 1),
(36, 'Clarify', 0),
(36, 'Reveal', 0),
(36, 'Explain', 0),

-- Q37
(37, 'Tangible', 1),
(37, 'Imaginary', 0),
(37, 'Invisible', 0),
(37, 'Abstract', 0),

-- Q38
(38, 'Detailed', 1),
(38, 'Incomplete', 0),
(38, 'Sketchy', 0),
(38, 'Approximate', 0),

-- Q39
(39, 'Unrepresentative', 1),
(39, 'Ideal', 0),
(39, 'Perfect example', 0),
(39, 'Archetypal', 0),

-- Q40
(40, 'Tough', 1),
(40, 'Fragile', 0),
(40, 'Weak', 0),
(40, 'Delicate', 0),

-- Q41
(41, 'Inspect', 1),
(41, 'Ignore', 0),
(41, 'Glance', 0),
(41, 'Overlook', 0),

-- Q42
(42, 'Yielding', 1),
(42, 'Persistent', 0),
(42, 'Determined', 0),
(42, 'Resolute', 0),

-- Q43
(43, 'Perceptive', 1),
(43, 'Shallow', 0),
(43, 'Vague', 0),
(43, 'Superficial', 0),

-- Q44
(44, 'Omnipresent', 1),
(44, 'Rare', 0),
(44, 'Occasional', 0),
(44, 'Scarce', 0),

-- Q45
(45, 'Respected', 1),
(45, 'Dishonored', 0),
(45, 'Unworthy', 0),
(45, 'Insignificant', 0),

-- Q46
(46, 'Cautious', 1),
(46, 'Reckless', 0),
(46, 'Bold', 0),
(46, 'Careless', 0),

-- Q47
(47, 'Fear of foreigners', 1),
(47, 'Love of travel', 0),
(47, 'Fear of animals', 0),
(47, 'Hate of politics', 0),

-- Q48
(48, 'Eloquent', 1),
(48, 'Awkward', 0),
(48, 'Inarticulate', 0),
(48, 'Clumsy', 0),

-- Q49
(49, 'Stubborn', 1),
(49, 'Flexible', 0),
(49, 'Compliant', 0),
(49, 'Submissive', 0),

-- Q50
(50, 'Passionate', 1),
(50, 'Apathetic', 0),
(50, 'Disinterested', 0),
(50, 'Indifferent', 0),

-- Q51
(51, 'Anomaly', 1),
(51, 'Routine', 0),
(51, 'Habit', 0),
(51, 'Tradition', 0),

-- Q52
(52, 'Peaceful', 1),
(52, 'Aggressive', 0),
(52, 'Hostile', 0),
(52, 'Combative', 0),

-- Q53
(53, 'Unexpected', 1),
(53, 'Normal', 0),
(53, 'Typical', 0),
(53, 'Predictable', 0),

-- Q54
(54, 'Agree', 1),
(54, 'Disagree', 0),
(54, 'Reject', 0),
(54, 'Deny', 0),

-- Q55
(55, 'Hardworking', 1),
(55, 'Lazy', 0),
(55, 'Negligent', 0),
(55, 'Sluggish', 0),

-- Q56
(56, 'Imitate', 1),
(56, 'Invent', 0),
(56, 'Ignore', 0),
(56, 'Abandon', 0),

-- Q57
(57, 'Accidental', 1),
(57, 'Planned', 0),
(57, 'Deliberate', 0),
(57, 'Intentional', 0),

-- Q58
(58, 'Hopeful', 1),
(58, 'Pessimistic', 0),
(58, 'Negative', 0),
(58, 'Hopeless', 0),

-- Q59
(59, 'Introverted', 1),
(59, 'Sociable', 0),
(59, 'Outgoing', 0),
(59, 'Talkative', 0),

-- Q60
(60, 'Arrogant', 1),
(60, 'Humble', 0),
(60, 'Modest', 0),
(60, 'Respectful', 0);




